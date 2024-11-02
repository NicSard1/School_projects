/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;

/**
 *
 * @author dckt2
 */
public interface MemberDao {
    public List<Member> getAllMembers();

    public void addMember(Member member);

    public Member getMember(int memberId);

    public void updateMember(Member member);

    public void deleteMember(Member member); 
}
