let g:deoplete#enable_at_startup = 1
set completeopt-=preview
inoremap <expr><tab> pumvisible() ? "\<C-n>" : "\<tab>"
inoremap <expr><S-tab> pumvisible() ? "\<C-p>" : "\<S-tab>"

" For <Enter>
" inoremap <expr><Tab> pumvisible() ? "\<DOWN>" : "\<Tab>"
" inoremap <expr><S-Tab> pumvisible() ? "\<UP>" : "\<S-Tab>"
