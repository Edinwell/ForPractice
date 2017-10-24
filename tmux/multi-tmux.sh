#!/bin/bash

function tdockers () {
    tab
    tmux new -s dockers
    tmux rename-window -t dockers:0 SER
    tmux send-keys -t dockers:0 'echo "hello SER"' C-m
    tmux new-window -t dockers
    tmux rename-window -t dockers:1 CLI
    tmux send-keys -t dockers:1 'echo "hello CLI"' C-m
}

function panes () {
    tmux new-window -t 0
}
