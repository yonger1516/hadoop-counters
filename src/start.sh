#!/bin/bash

ps -ef|grep 19888|awk '{print $2}'|xargs kill -9

../../../enable_cdh_port.sh 29888 19888

groovy main.groovy
