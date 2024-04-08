package controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.dto.MemberDto;
import service.MemberService;
import service.MemberServiceImpl;

public class MemberController implements SubController {
    private MemberService memberService;

    public MemberController() {

        try {
            memberService = MemberServiceImpl.getInstance();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    // 1 Insert , 2 Update , 3 Delete 4 SelectAll 5 Select 6 Login 7 Logout
    @Override
    public Map<String, Object> execute(int serviceNo, Map<String, Object> params) {
        System.out.println("MemberController's execute()");

        switch (serviceNo) {
            // Insert
            case 1:
                // 파라미터
                MemberDto dto = (MemberDto) params.get("Insert");
                // 유효성
                if (dto==null)
                    return null;
                // 서비스
                boolean isSignUp = false;
                try {
                    isSignUp = memberService.signUp(dto);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 뷰
                Map<String, Object> result = new HashMap<>();
                result.put("response", isSignUp);
                return result;

            // Update
            case 2:
                // 파라미터
                MemberDto updateDto = (MemberDto) params.get("Update");
                // 유효성
                if (updateDto == null) {
                    return null;
                }
                // 서비스
                boolean isUpdated = false;
                try {
                    isUpdated = memberService.updateProfile(updateDto);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 뷰
                Map<String, Object> updateResult = new HashMap<>();
                updateResult.put("response", isUpdated);
                return updateResult;

            // Delete
            case 3:
                // 파라미터
                MemberDto deleteDto = (MemberDto) params.get("Delete");
                // 유효성
                if (deleteDto == null) {
                    System.out.println("check");
                    return null;
                }
                // 서비스
                Boolean isSuccess = true;
                try {
                    isSuccess = memberService.deleteProfile(deleteDto);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 뷰
                Map<String, Object> deleteResult = new HashMap<>();
                deleteResult.put("response", isSuccess);
                return deleteResult;

            // SelectAll
            case 4:
                // 파라미터
                Object obj = params.get("SelectAll");
                // 유효성
                // 서비스
                List<MemberDto> view = null;
                try {
                    view = memberService.getAllUsers();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                // 뷰
                Map<String, Object> allUsersResult = new HashMap<>();
                allUsersResult.put("response", view);
                return allUsersResult;

            // Select
            case 5:
                // // 파라미터
                // MemberDto insertDto = (MemberDto) params.get("Select");
                // String name = insertDto.getUsername();
                // // 유효성
                // if (!true)
                // return null;
                // MemberDto Selected = null;
                // // 서비스
                // try {
                // Selected = memberService.getProfile(name);
                // } catch (Exception e) {
                // e.printStackTrace();
                // }
                // // 뷰
                // Map<String,Object> selectResult = new HashMap<>();
                // selectResult.put("response", Selected);
                break;

            // SignIn
            case 6:
                // 파라미터
                MemberDto signInDto = (MemberDto) params.get("SignIn");
                // 유효성
                if (signInDto == null)
                    return null;
                // 서비스
                Map<String, Object> signInServiceResult = null;
                try {
                    signInServiceResult = memberService.signIn(signInDto);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 뷰
                Map<String, Object> signInResult = new HashMap<>();
                signInResult.put("response", signInServiceResult);
                return signInResult;

            // SignOut
            case 7:
                // 파라미터
                MemberDto signOutDto = (MemberDto) params.get("SignOut");
                // 유효성
                if (signOutDto == null) {
                    return null;
                }
                // 서비스
                Map<String, Object> resultSignOut = null;
                try {
                    resultSignOut = memberService.signOut(signOutDto);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 뷰
                return resultSignOut;
            default:
                break;
        }
        return null;
    }

}
