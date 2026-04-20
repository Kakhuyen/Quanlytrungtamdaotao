# Quanlytrungtamdaotao

Hệ thống quản lý trung tâm đào tạo viết bằng Java Servlet, JSP, Gradle và MySQL.

## Yêu cầu

- JDK 8
- MySQL Server
- Apache Tomcat 8.5+ hoặc 9
- Không cần cài Gradle riêng, dự án đã có Gradle Wrapper

## Cấu hình database

Dự án đọc cấu hình theo thứ tự sau:

1. Biến môi trường:
   - `DB_URL`
   - `DB_USER`
   - `DB_PASSWORD`
2. Java system properties:
   - `db.url`
   - `db.user`
   - `db.password`
3. File cấu hình:
   - `src/main/resources/db.properties`

File mẫu đã có sẵn:

- `src/main/resources/db.properties.example`

Copy file này thành `db.properties`, sau đó sửa theo máy của bạn.

Ví dụ:

```properties
db.url=jdbc:mysql://localhost:3306/quanlytrungtam?useSSL=false&serverTimezone=UTC
db.user=root
db.password=123456
```

## Import database

1. Tạo database MySQL tên `quanlytrungtam`
2. Import file SQL trong thư mục `Database/`
3. Kiểm tra lại user, password và URL kết nối trong `db.properties`

## Build project

Trên Windows:

```bat
gradlew.bat clean build
```

Hoặc tạo WAR:

```bat
gradlew.bat clean war
```

File WAR sẽ nằm ở:

- `build/libs/quanlytrungtam-1.0-SNAPSHOT.war`

## Deploy lên Tomcat

1. Copy file WAR vào thư mục `webapps` của Tomcat
2. Start Tomcat
3. Mở trình duyệt:

```text
http://localhost:8080/quanlytrungtam-1.0-SNAPSHOT/
```

## Cấu trúc thư mục chính

- `src/main/java/controller`: Servlet xử lý request
- `src/main/java/dao`: truy cập dữ liệu
- `src/main/java/model`: model/entity
- `src/main/java/util`: DBContext và tiện ích
- `src/main/java/filter`: filter phân quyền
- `src/main/webapp`: JSP và tài nguyên giao diện
- `Database`: file SQL khởi tạo CSDL

## Ghi chú

- Không commit file `src/main/resources/db.properties` vì đây là cấu hình local
- Dùng `db.properties.example` để tạo file cấu hình riêng cho từng máy
- Nếu đổi tên database hoặc thông tin MySQL, hãy cập nhật lại `db.properties`
