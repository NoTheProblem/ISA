package ftn.isa.pharmacy.dto;

import java.util.Date;

public class EPrescriptionDTO {
    private Long id;
    private String code;

    public EPrescriptionDTO() {
    }

    public EPrescriptionDTO(Long id, String code, float price, String status, Date dateOfIssue, MedicineDto medicineDto, PharmacyDto pharmacyDto, PatientDTO patientDTO) {
        this.id = id;
        this.code = code;
        this.price = price;
        this.status = status;
        this.dateOfIssue = dateOfIssue;
        this.medicineDto = medicineDto;
        this.pharmacyDto = pharmacyDto;
        this.patientDTO = patientDTO;
    }

    private float price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public MedicineDto getMedicineDto() {
        return medicineDto;
    }

    public void setMedicineDto(MedicineDto medicineDto) {
        this.medicineDto = medicineDto;
    }

    public PharmacyDto getPharmacyDto() {
        return pharmacyDto;
    }

    public void setPharmacyDto(PharmacyDto pharmacyDto) {
        this.pharmacyDto = pharmacyDto;
    }

    public PatientDTO getPatientDTO() {
        return patientDTO;
    }

    public void setPatientDTO(PatientDTO patientDTO) {
        this.patientDTO = patientDTO;
    }

    private String status;
    private Date dateOfIssue;
    private MedicineDto medicineDto;
    private PharmacyDto pharmacyDto;
    private PatientDTO patientDTO;
}
