#!/bin/bash

function dps () {
  command docker ps
}

function dpa () {
  command docker ps -a
}

function dim () {
  command docker images
}

function dres () {
  command docker restart $1
}

function dstop () {
  if [ "$1" = "" ];then
    docker stop $(docker ps -a -q)
  else
    command docker stop $1
  fi
}

function dbash () {
    command docker exec -it $1 /bin/bash
}
