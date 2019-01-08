let g:deoplete#enable_at_startup = 1
set completeopt-=preview
imap <expr><TAB> pumvisible() ? "\<C-N>" : neosnippet#jumpable() ?  "\<Plug>(neosnippet_expand_or_jump)" : "\<TAB>"
smap <expr><TAB> neosnippet#jumpable() ?  "\<Plug>(neosnippet_expand_or_jump)" : "\<TAB>"
inoremap <expr><S-TAB>  pumvisible() ? "\<C-p>" : "\<S-TAB>"

" For <Enter>
" inoremap <expr><Tab> pumvisible() ? "\<DOWN>" : "\<Tab>"
" inoremap <expr><S-Tab> pumvisible() ? "\<UP>" : "\<S-Tab>"
