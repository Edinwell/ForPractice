if &compatible
  set nocompatible
endif

augroup MyAutoCmd
  autocmd!
augroup END

"python paths----------------------------
let g:python_host_prog = '/usr/local/bin/python2'
let g:python3_host_prog = '/usr/local/bin/python3'

"dein Scripts-----------------------------
let s:dein_cache_path = expand('~/.cache/nvim/dein')
let s:dein_dir = s:dein_cache_path
                 \ .'/repos/github.com/Shougo/dein.vim'

if &runtimepath !~ '/dein.vim'
  if !isdirectory(s:dein_dir)
    execute '!git clone https://github.com/Shougo/dein.vim' s:dein_dir
  endif
  execute 'set runtimepath+=' . fnamemodify(s:dein_dir, ':p')
endif

if dein#load_state(s:dein_cache_path)
  call dein#begin(s:dein_cache_path)

  call dein#load_toml('~/.config/nvim/dein.toml', {'lazy' : 0})
  call dein#load_toml('~/.config/nvim/dein_lazy.toml', {'lazy' : 1})

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
nnoremap <Space> o<Esc>
nnoremap ~ ~h

" Settings ----------------------------
colorscheme wombat256

augroup HighlightTrailingSpaces
  autocmd!
  autocmd VimEnter,WinEnter,ColorScheme * highlight TrailingSpaces term=underline guibg=Gray ctermbg=Gray
  autocmd VimEnter,WinEnter * match TrailingSpaces /\s\+$/
augroup END

" Settings ----------------------------
colorscheme wombat256
set number
set ambiwidth=double
set expandtab
set tabstop=4
set shiftwidth=4
