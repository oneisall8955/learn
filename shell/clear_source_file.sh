#!/bin/bash -e

this_shell_dir=$(cd $(dirname $0); pwd)

echo "输入需要清理的已刮削的文件夹："

read gen_folder

if [ -z $gen_folder ]; then
    echo "未输入gen_folder"
    exit
fi

if [ ! -d "$gen_folder" ]; then
    echo "gen_folder文件夹不存在"
    exit
fi
gen_folder_name=$(basename $gen_folder)
if [ -z $gen_folder_name ]; then
    echo "异常，target文件夹的名称为空"
    exit
fi

gen_files_record_file="$gen_folder/.gen.record"
echo "生成的文件所有记录在=$gen_files_record_file"
if [ ! -f "$gen_files_record_file" ]; then
    echo "生成的文件所有记录 $gen_files_record_file 文件不存在"
    exit
fi

to_upload_folder="$this_shell_dir/${gen_folder_name}_scrape_to_upload"

if read -t 10 -p "输入1/y确认删除并重新复制文件夹=$to_upload_folder：" input
then
    if [ $input == "1" ] || [ $input == "y" ]; then
        echo "删除=$to_upload_folder"
        rm -rf $to_upload_folder
        echo "复制=$to_upload_folder"
        cp -r "$gen_folder" "$to_upload_folder"
    else
        echo "选择不重建，结束"
        exit
    fi
else
    echo "未确认，结束"
    exit
fi

files=$(cat $gen_files_record_file)

for filename in $files
do
    file_path="$to_upload_folder/$filename"
    echo "删除原文件=$file_path"
    rm -rf $file_path
done

to_delete_gen_files_record_file="$to_upload_folder/.gen.record"
echo "删除记录文件=$to_delete_gen_files_record_file"
rm -rf $to_delete_gen_files_record_file

echo "已清理完毕，文件夹在=$to_upload_folder"
