(1)1�s��80����

�~�i����͒����j
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

���i���̂P�j
public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

���i���̂Q�j
public void doGet(HttpServletRequest  request,
					HttpServletResponse response)
					throws ServletException, IOException {

(2)�C���f���g

int k = 0;
for (int i = 0; i < 10; i++) {
	for (int j = 0; j < 10; j++) {
		if(i == 0) {
			k = 1;
		} else if (i == 1) {
			if(j == 0) {
				k = 2;
			}
		}
		k = 3;
	}
}

(3)���₷�����ꂽ�K�x�ȃX�y�[�X

�~
for(int i=0;i<10;i++){

��
for (int i = 0; i < 10; i++) {

�ȏ�
