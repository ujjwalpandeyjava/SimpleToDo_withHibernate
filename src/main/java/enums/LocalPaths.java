package enums;

public enum LocalPaths {
//	UPLOAD_PATH("host-file-direcotry/Server/apache-tomcat-10.1.8/webapps/uploadedFiles/");

//	UPLOAD_PATH("C:/Users/ujjwa/All Files/Software/IDEs/Server/apache-tomcat-10.1.8/webapps/"),
//	CURRENTHOST("http://localhost:8081");
	UPLOAD_PATH("C:/Users/ujjwa/All Files/Software/IDEs/Server/apache-tomcat-10.1.8/webapps/"),
	CURRENTHOST("http://localhost:8080");

	private String value;

	public String getValue() {
		return this.value;
	}

	// enum constructor - Cannot be public or protected
	private LocalPaths(String value) {
		this.value = value;
	}
}
