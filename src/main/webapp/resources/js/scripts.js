function verificar(xhr, status, args, dlg) {
	if (args.validationFailed) {
		PF(dlg).hide();
	} else {
		PF(dlg).show();
		// PF(tbl).clearFilters();
	}
}

function moeda(z) {
	v = z.value;
	v = v.replace(/\D/g, "") // permite digitar apenas numero
	v = v.replace(/(\d{1})(\d{1,2})$/, "$1,$2") // coloca virgula antes dos
												// ultimos 2 digitos
	z.value = v;
}
