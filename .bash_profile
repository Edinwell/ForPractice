# Env vars.
export XDG_CACHE_HOME='$HOME/.cache'
export XDG_CONFIG_HOME='$HOME/.config'
export LDFLAGS=-L/usr/local/opt/curl/lib
export CPPFLAGS=-I/usr/local/opt/curl/include
export PATH="/usr/local/opt/python/libexec/bin:$PATH"
export LANG=ja_JP.UTF-8

# Git.
source /usr/local/etc/bash_completion.d/git-completion.bash
source /usr/local/etc/bash_completion.d/git-prompt.sh

GIT_PS1_SHOWDIRTYSTATE=1
GIT_PS1_SHOWUPSTREAM=1
GIT_PS1_SHOWUNTRACKEDFILES=
GIT_PS1_SHOWSTASHSTATE=1
export PS1='\[\033[1;33m\][ \w ]\[\033[1;31m\]$(__git_ps1)\n\[\033[1;32m\]\u\[\033[00m\]:\[\033[1;34m\]\W\[\033[00m\]\$ '

. ~/.bashrc
