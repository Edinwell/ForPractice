# Alias
alias ls="ls -G"
alias ll="ls -laG"
alias ni="nvim"
alias niew="nvim -r"

# Run tmux only there is no tmux-session
if [ -z "$TMUX" ]; then
    if $(tmux has-session); then
        tmux ls
    else
        exec tmux new -s work
    fi
fi
