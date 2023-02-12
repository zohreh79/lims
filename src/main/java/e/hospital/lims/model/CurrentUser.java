package e.hospital.lims.model;

import e.hospital.lims.domain.SystemRole;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class CurrentUser {

    private SystemRole role;
    private Long doctorId;

}
