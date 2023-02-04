#!/bin/bash -e

this_shell_dir=$(cd $(dirname $0); pwd)

echo "输入云盘资源文件夹："

read target_folder

if [ -z $target_folder ]; then
    echo "未输入target_folder"
    exit
fi

if [ ! -d "$target_folder" ]; then
    echo "target文件夹不存在"
    exit
fi

target_folder=$(cd $target_folder;pwd)

files=$(ls $target_folder |sort)

target_folder_name=$(basename $target_folder)

if [ -z $target_folder_name ]; then
    echo "异常，target文件夹的名称为空"
    exit
fi

gen_folder="$this_shell_dir/$target_folder_name"

echo "初始化生成的文件夹=$gen_folder"

if read -t 10 -p "输入1/y确认删除并创建文件夹=$gen_folder:" input
then
    if [ $input == "1" ] || [ $input == "y" ]; then
        echo "确认初始化=$gen_folder，先删除再创建！"
        echo "删除=$gen_folder"
        rm -rf $gen_folder
        echo "创建=$gen_folder"
        mkdir -p $gen_folder
    else
        echo "选择不初始化，结束"
        exit
    fi
else
    echo "未确认，结束"
    exit
fi


gen_files_record_file="$gen_folder/.gen.record"
echo "生成的文件所有记录在=$gen_files_record_file"

##############################################

# for filename in $files
# do
#     file_path="$target_folder/$filename"
#     if [ -d "$file_path" ]; then
#         echo "$file_path 是文件夹，不递归生成"
#     elif [ -f "$file_path" ]; then
#         gen_file="$gen_folder/$filename"
#         echo "创建文件=$gen_file"
#         echo "generate from $file_path" > $gen_file
#         echo "$filename" >> $gen_files_record_file
#     else
#         echo "$file_path 文件属性未知"
#     fi
# done

# echo "生成的文件所有记录，$gen_files_record_file，如下:"
# cat $gen_files_record_file

#############################################


# https://www.reneelab.com.cn/video-extention.html
ext_array=("mp4" "m4v" "mov" "qt" "avi" "flv" "wmv" "asf" "mpeg" "mpg" "vob" "mkv" "asf" "wmv" "rm" "rmvb" "vob" "ts" "dat")

declare -A ext_map=()

for ext_name in ${ext_array[@]}; do
    ext_map["$ext_name"]=$ext_name
done

echo ${!ext_map[@]}

ret="-1"
function allow_file(){
    ret="-1"
    file_name=$1
    file_ext_name="${file_name##*.}"
    ext_name=${ext_map["$file_ext_name"]}
    if [ -z "$ext_name" ]; then
        ret="0"
    else
        ret="1"
    fi
}

# readDir() {
#     dir=$1
#     files=$(ls "$dir" |sort)
#     for file in $files; do
#         path="$dir/$file"
#         if [ -d "$path" ]; then
#             path=${path: 2}
#             echo "$path/"
#             gen_folder_path="$gen_folder/$path/"
#             if [ ! -d $gen_folder_path ]; then
#                 echo "创建文件夹=$gen_folder_path"
#                 mkdir -p $gen_folder_path
#                 echo "$gen_folder_path" >> $gen_files_record_file
#             fi
#             readDir "$path"
#         else
#             allow_file $path
#             if [ "$ret" == "0" ] || [ "$ret" == "-1" ]; then
#                 echo "$path 不是视频文件或判断失败，跳过"
#             else
#                 echo "$path"
#                 gen_file="$gen_folder/$path"
#                 echo "创建文件=$gen_file"
#                 echo "generate from $target_folder/$path" > $gen_file
#                 echo "$gen_file" >> $gen_files_record_file
#             fi
#         fi
#     done
# }

echo "################### 开始处理 ###################"
echo ""
echo ""
echo ""

readDir() {
    local dir=$1
    local files
    files=$(ls "$dir")
    for file in $files; do
        local path="$dir/$file"

        if [[ $path == ./* ]]; then
            path=${path: 2}
        fi

        if [ -d "$path" ]; then
            echo "##### 开始处理 $path/"
            gen_folder_path="$gen_folder/$path/"
            echo "创建子文件夹=$gen_folder_path"
            ## 创建文件夹
            if [ ! -d $gen_folder_path ]; then
                mkdir -p $gen_folder_path
                echo "$gen_folder_path" >> $gen_files_record_file
            fi
            readDir "$path"
            echo "##### 结束处理 $path/"

        else
            echo "$path"
            allow_file $path
            if [ "$ret" == "0" ] || [ "$ret" == "-1" ]; then
                echo "$path 不是视频文件或判断失败，跳过，ret=$ret"
            else
                gen_file="$gen_folder/$path"
                echo "创建文件=$gen_file"
                ## 创建文件
                echo "generate from $target_folder/$path" > $gen_file
                echo "$gen_file" >> $gen_files_record_file
            fi
        fi
    done
}

cd $target_folder

readDir .


echo ""
echo ""
echo ""

echo "################### 结束处理 ###################"

echo "生成的文件所有记录，$gen_files_record_file，如下:"
cat $gen_files_record_file
