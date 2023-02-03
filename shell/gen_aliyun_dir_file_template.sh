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

for filename in $files
do
    file_path="$target_folder/$filename"
    if [ -d "$file_path" ]; then
        echo "$file_path 是文件夹，不递归生成"
    elif [ -f "$file_path" ]; then
        gen_file="$gen_folder/$filename"
        echo "创建文件=$gen_file"
        echo "generate from $file_path" > $gen_file
        echo "$filename" >> $gen_files_record_file
    else
        echo "$file_path 文件属性未知"
    fi
done

echo "生成的文件所有记录，$gen_files_record_file，如下:"
cat $gen_files_record_file
