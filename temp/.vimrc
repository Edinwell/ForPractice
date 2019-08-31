" Key bindings-------------------------
inoremap jj <Esc>
nnoremap <Space> o<Esc>
nnoremap ~ ~h

" Settings ----------------------------
"colorscheme wombat256

augroup HighlightTrailingSpaces
  autocmd!
  autocmd VimEnter,WinEnter,ColorScheme * highlight TrailingSpaces term=underline guibg=Gray ctermbg=Gray
  autocmd VimEnter,WinEnter * match TrailingSpaces /\s\+$/
augroup END

" Settings ----------------------------
set number
set ambiwidth=double
set expandtab
set tabstop=4
set shiftwidth=4
filetype plugin indent on
syntax enable

