# 删除 /opt/soft/log/ 目录下 7 天前的日志
find /opt/soft/log/ -mtime +7 -name "*.log" -exec rm -rf {} \;

# 去重 tmp.txt 是文本
awk '!a[$0]++' tmp.txt
