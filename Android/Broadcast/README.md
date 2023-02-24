### Font Vietnamese please ###
# Lý do mình dùng tiếng việt thì cũng chẳng có gì quan trọng đâu. Chỉ là để cho nó nhanh hơn, thế thôi ạ :D #

** Ok trước đó là lời giới thiệu. Thực sự mình cũng chưa biết mình sẽ làm gì với cái project này nhưng về cơ bản thì nó có những mục đích như sau.
 > Dùng đề hiểu về cách thức hoạt động cũng như hiểu về ứng dụng của Broadcast trong Android để làm gì.
 > Sử dụng Broadcast một cách đơn giản và dễ hiểu nhất, điền đầy đủ comment và lý do, những yêu cầu cần thiết và không cần thiết ứng với một dự án như thế này.
 > Nếu Có bất cứ thứ gì là yêu cầu bắt buộc, hãy ghi nó vào.
 > Vào có thể sẽ phải điền thêm vào đây như một bản ghi chú nhỏ. Làm ơn hãy làm thế!

 Và giờ thì "Let's Start!"


1. Tạo project trống.
Tạo một project Android mới (Empty activity).

2. Gặp lỗi build
Lỗi: ""

Đây là lỗi thường gặp khi build ứng dụng trên windows. Để sửa cần vào {LINK SKD FOLDER}\\build-tools\\{version build}
LINK_SDK default: C:\Users\%User%\AppData\Local\Android\Sdk\build-tools\
Sửa lại tên những file file d8.jar d8.jar, d8.bat thành dx.jar, dx.bat là được :v

Issue:
"Can't determine type for tag '<macro name="m3_comp_assist_chip_container_shape">?attr/shapeAppearanceCornerSmall</macro>'"
3. Tạo Activity mới và trống nhỉ? Chắc sẽ là MainScreen
 - Không được, theo luật code thì nên để thêm chữ Activity đằng sau để biết đó là một Activity, Class là class, ... 