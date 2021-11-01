package in.natureom.plantcare.usermgmt.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "natureom_role")
@AllArgsConstructor
@NoArgsConstructor
public class NatureOmRole {
    @Id
    @Column(name = "role_id")
    public Long roleId;

    @Column(name = "role_name")
    public String roleName;

    @Column(name = "role_description")
    public String roleDescription;

    
}
