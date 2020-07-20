#!/bin/expect
set host [lindex $argv 0]
set user [lindex $argv 1]
set password [lindex $argv 2]
set timeout 30
spawn ssh $user@$host
expect {
    "yes/no" { send "yes\n";exp_continue}
    "password:" { send "$password\n" }
}
interact
