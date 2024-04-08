import java.util.HashMap;
import java.util.Map;

import controller.FrontController;

public class Application {
    public static void main(String[] args) throws Exception {

        FrontController controller = new FrontController();
        Map<String, Object> params = new HashMap<>();
        // 회원가입
        // params.put("Insert", new MemberDto("지영", "1111", "test1@test.com",
        // 1011111111));
        // Map<String,Object> test1 = controller.execute("/member", 1, params);
        // System.out.println(test);
        // params.put("Insert", new MemberDto("재형", "2222", "test2@test.com",
        //         1022222222));
        // Map<String, Object> test2 = controller.execute("/member", 1, params);
        // System.out.println(test2);

        // 모든 사용자 조회
        // params.put("SelectAll", new Object());
        // Map<String, Object> test3 = controller.execute("/member", 4, params);
        // System.out.println(test3);

        // 회원 정보 수정
        // params.put("Update", new MemberDto("지영", "1111", "test3@test.com",
        // 1011112222));
        // Map<String, Object> test4 = controller.execute("/member", 2, params);
        // System.out.println(test3);

        // 회원 정보 삭제(회원탈퇴)
        // params.put("Delete", new MemberDto("재형", "1111"));
        // Map<String, Object> test5 = controller.execute("/member", 3, params);
        // System.out.println(test5);

        // 로그인
        // params.put("SignIn", new MemberDto("지영", "1111"));
        // Map<String,Object> test = controller.execute("/member", 6, params);
        // System.out.println(test);
        // params.put("SignIn", new MemberDto("재형", "2222"));
        // Map<String,Object> test2 = controller.execute("/member", 6, params);
        // System.out.println(test2);

        // 로그아웃
        // params.put("SignOut", new MemberDto("지영", "1111"));
        // Map<String, Object> test2 = controller.execute("/member", 7, params);
        // System.out.println(test2);
    }
}
