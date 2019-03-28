let g:lightline = {
	\ 'colorscheme': 'wombat',
		\ 'active': {
			\ 'left': [ ['mode', 'paste'], ['readonly', 'filepath', 'modified'] ]
				\ },
		\ 'component_function':{
			\ 'filepath': 'FilePath'
				\ }
		\ }

function! FilePath()
    return winwidth(0) > 40 ? expand("%:s") : expand("%:t")
endfunction
