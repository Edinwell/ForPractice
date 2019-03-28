set hidden

" settings for languages
let g:LanguageClient_serverCommands = {}

if executable('clangd')
  let g:LanguageClient_serverCommands['cpp'] = ['clangd']
endif
if executable('pyls')
  let g:LanguageClient_serverCommands['python'] = ['pyls']
endif

augroup LanguageClient_config
  autocmd!
  autocmd User LanguageClientStarted setlocal signcolumn=yes
  autocmd User LanguageClientStopped setlocal signcolumn=auto
augroup END

let g:LanguageClient_windowLogMessageLevel = "Warning"
let g:LanguageClient_useFloatingHover = 1
let g:LanguageClient_autoStart = 1

nnoremap <silent> <C-w>e :call LanguageClient#explainErrorAtPoint()<CR>
nnoremap <silent> K :call LanguageClient_textDocument_hover()<CR>
nnoremap <silent> <C-w>d :call LanguageClient_textDocument_definition()<CR>
nnoremap <silent> <F2> :call LanguageClient_textDocument_rename()<CR>
nnoremap <silent> <F3> :call LanguageClient_textDocument_formatting()<CR>
