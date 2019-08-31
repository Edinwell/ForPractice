# Git compeletion for bash.
. /usr/local/etc/bash_completion.d/git-completion.bash
. /usr/local/etc/bash_completion.d/git-prompt.sh

# Alias
for src in `find $XDG_CONFIG_HOME/self -type f`; do
  source $src
done

# Run tmux only first time.
if [ "$(uname) = 'Darwin'" ] && [ $SHLVL = 1 ]; then
  tmux
fi
