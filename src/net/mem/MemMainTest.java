package net.mem;

import java.io.InputStream;
import java.util.List;

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
			System.out.println("DB Connect Success"+"\n");
			
			// 쿼리문 생성
			SqlSession sql=ssf.openSession(true);
			
			
			// 쿼리문 실행
			
			// 추가
			int result=sql.insert("mem.insertRow", new MemDTO("해액", 999));
			System.out.println("★행 추가 결과: "+result);

			
			// 수정
			result=sql.update("mem.updateRow", new MemDTO(1, "어피치", 25));
			System.out.println("★행 수정 결과: "+result);

			
			// 삭제
			result=sql.delete("mem.deleteRow", 10);
			System.out.println("★행 삭제 결과: "+result);

			
			// 9 이상 행 삭제
			result=sql.delete("mem.deleteRow2", 9);
			System.out.println("★행 삭제 2 결과: "+result);
			
			
			// 목록 조회
			System.out.println("\n★목록 조회 결과: ");
			List<MemDTO> list=sql.selectList("mem.selectAll");
			
			for(int idx=0; idx<list.size(); idx++) {
				MemDTO dto=list.get(idx);
				System.out.print(dto.getNum()+ " ");
				System.out.print(dto.getName()+ " ");
				System.out.println(dto.getAge());
			}
			
			
			// 상세 조회
			System.out.println("\n★상세 조회 결과: ");
			MemDTO dto=sql.selectOne("mem.selectRead", new MemDTO(1));
			System.out.print(dto.getNum()+ " ");
			System.out.print(dto.getName()+ " ");
			System.out.println(dto.getAge());
			
			
			// 검색
			System.out.println("\n★검색 결과: ");
			list=sql.selectList("mem.search", "이");
			
			for(int idx=0; idx<list.size(); idx++) {
				dto=list.get(idx);
				System.out.print(dto.getNum()+ " ");
				System.out.print(dto.getName()+ " ");
				System.out.println(dto.getAge());
			}
			
			System.out.println("\n★레코드개수: "+sql.selectOne("mem.rowCount"));
			
			
			
			
		}catch(Exception e) {
			System.out.println("Failed: "+e);
		}
		
		
	}
}
