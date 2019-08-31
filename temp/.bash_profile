# XDG Base Directory support
export XDG_CONFIG_HOME=$HOME/.config
export XDG_CACHE_HOME=$HOME/.cache
export XDG_DATA_HOME=$HOME/.local/share
export LDFLAGS="-L/usr/local/opt/llvm/lib"
export CPPFLAGS="-I/usr/local/opt/llvm/include"
export kegs="/usr/local/opt/openssl/bin:/usr/local/opt/gettext/bin"


# Show git detail on PS1
GIT_PS1_SHOWDIRTYSTATE=1
GIT_PS1_SHOWUPSTREAM=1
GIT_PS1_SHOWUNTRACKEDFILES=1
GIT_PS1_SHOWSTASHSTATE=1
export PS1='\[\e[0;35m\]\w\[\e[0m\033[1;34m\]$(__git_ps1)\[\e[1;32m\]\n\H\[\033[00m\] \$ '

# The default PATH
export PATH="/usr/local/bin:/usr/local/sbin:/usr/bin:/bin:/usr/sbin:/sbin"

# Additional
export PATH="/usr/local/opt/gettext/bin:/usr/local/opt/llvm/bin:$HOME/Library/Python/2.7/bin:$PATH"

if [ -f ~/.bashrc ]; then
    . ~/.bashrc
fi

###### Optionals disabled ######
#For compilers to find gettext you may need to set:
#  export LDFLAGS="-L/usr/local/opt/gettext/lib"
#  export CPPFLAGS="-I/usr/local/opt/gettext/include"
