package net.mem;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MemMainTest {
	
	public static void main(String[] args) {
		
		try {
			
			// DB 환경설정 관련 파일 가져오기
			String resource="config/jdbc.xml";
			InputStream is=Resources.getResourceAsStream(resource);
			
			// DB 연결을 위한 Factory bean 생성
			SqlSessionFactory ssf=new SqlSessionFactoryBuilder().build(is);
			System.out.println("DB Connect Success");
			
			// 쿼리문 생성
			SqlSession sql=ssf.openSession(true);
			
			// 쿼리문 실행
			int result=sql.insert("mem.insertRow", new MemDTO("해액", 999));
			System.out.println("행 추가 결과: "+result);
			
		}catch(Exception e) {
			System.out.println("Failed: "+e);
		}
		
		
	}
}
