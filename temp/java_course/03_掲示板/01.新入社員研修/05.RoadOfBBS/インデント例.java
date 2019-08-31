(1)1行は80字で

×（これは長い）
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

◎（その１）
public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

◎（その２）
public void doGet(HttpServletRequest  request,
					HttpServletResponse response)
					throws ServletException, IOException {

(2)インデント

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

(3)見やすく入れた適度なスペース

×
for(int i=0;i<10;i++){

◎
for (int i = 0; i < 10; i++) {

以上
