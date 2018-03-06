export XDG_CONFIG_HOME=~/.config
export XDG_CACHE_HOME=~/.cache

# Git
. $HOME/.git-completion.bash
. $HOME/.git-prompt.sh

GIT_PS1_SHOWDIRTYSTATE=1
GIT_PS1_SHOWUPSTREAM=1
GIT_PS1_SHOWUNTRACKEDFILES=1
GIT_PS1_SHOWSTASHSTATE=1
export PS1='\[\e[0;35m\]\w\[\e[0m\033[1;34m\]$(__git_ps1)\[\e[1;32m\]\n\H\[\033[00m\] \$ '

# alias
alias ls="ls -G"
alias ll="ls -laG"
alias ni="nvim"
alias niew="nvim -R"

# run tmux only first time
if [ "$(uname) = 'Darwin'" ] && [ $SHLVL = 1 ]; then
  tmux
fi
