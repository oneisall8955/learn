#!/bin/bash -e
# 去重 tmp.txt 是文本
awk '!a[$0]++' tmp.txt
