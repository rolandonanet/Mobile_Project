package com.project.presence.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QRCodeCheckDTO {
    private String studentId;
    private String encodedQRCode;
}
