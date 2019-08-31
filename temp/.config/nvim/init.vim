if &compatible
  set nocompatible
endif

set runtimepath+=~/.cache/dein/repos/github.com/Shougo/dein.vim
if dein#load_state('~/.cache/dein')
  call dein#begin('~/.cache/dein')
  call dein#load_toml('~/.config/nvim/dein.toml', {'lazy': 0})
  call dein#load_toml('~/.config/nvim/dein_lazy.toml', {'lazy': 1})
  call dein#end()
  call dein#save_state()
endif

if dein#check_install()
 call dein#install()
endif

" Settings ----------------------------
filetype plugin indent on
syntax enable

colorscheme wombat256
set splitright
set number
set ambiwidth=double
set expandtab
set tabstop=2
set shiftwidth=2
set clipboard=unnamed
set hls

" Key bindings-------------------------
inoremap jj <Esc>
inoremap kk <Esc>la
nnoremap <Space> o<Esc>
nnoremap ~ ~h

" Comman expand -----------------------
augroup HighlightTrailingSpaces
  autocmd!
  autocmd VimEnter,WinEnter,ColorScheme * highlight TrailingSpaces term=underline guibg=Gray ctermbg=Gray
  autocmd VimEnter,WinEnter * match TrailingSpaces /\s\+$/
augroup END
