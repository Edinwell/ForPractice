" ----------------GVIM encoding bug solution-----------------
" source $VIMRUNTIME/delmenu.vim
" set langmenu=ja_jp.utf-8
" source $VIMRUNTIME/menu.vim

"python paths----------------------------
let g:python_host_prog = '/usr/local/bin/python2'
let g:python3_host_prog = '/usr/local/bin/python3'

"dein Scripts-----------------------------
if &compatible
  set nocompatible
endif

augroup MyAutoCmd
  autocmd!
augroup END

if has('nvim')
  let s:dein_cache_path = expand('~/.cache/dein')
endif

let s:dein_dir = expand('~/.config/nvim/dein/repos/github.com/Shougo/dein.vim')

if &runtimepath !~ '/dein.vim'
  if !isdirectory(s:dein_dir)
    execute '!git clone https://github.com/Shougo/dein.vim' s:dein_dir
  endif
  execute 'set runtimepath+=' . fnamemodify(s:dein_dir, ':p')
endif

if dein#load_state(s:dein_cache_path)
  call dein#begin(s:dein_cache_path)

  call dein#load_toml('~/.config/nvim/dein/toml/dein.toml', {'lazy' : 0})
  call dein#load_toml('~/.config/nvim/dein/toml/dein_lazy.toml', {'lazy' : 1})

  call dein#end()
  call dein#save_state()
endif

if dein#check_install()
  call dein#install()
endif

filetype plugin indent on
syntax enable

" Key bindings-------------------------
inoremap jj <Esc>
nnoremap 0 o<Esc>
nnoremap ~ ~h

" Settings ----------------------------
colorscheme wombat256
set number
set scrolloff=5
set encoding=utf-8

set ambiwidth=double
set expandtab
set tabstop=4
set shiftwidth=4

highlight ZenkakuSpace cterm=underline ctermfg=lightblue guibg=#666666
au BufNewFile,BufRead * match ZenkakuSpace /　/
set indentkeys=!^F,o,O,0<Bar>,0=where

" --color---
colorscheme wombat256
