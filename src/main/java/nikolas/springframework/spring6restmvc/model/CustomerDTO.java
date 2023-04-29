package nikolas.springframework.spring6restmvc.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CustomerDTO {

    private UUID id;
    private String customerName;
    private Integer version;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    CustomerDTO(UUID id, String customerName, Integer version, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.customerName = customerName;
        this.version = version;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public static CustomerBuilder builder() {
        return new CustomerBuilder();
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public UUID getId() {
        return this.id;
    }

    public Integer getVersion() {
        return this.version;
    }

    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CustomerDTO)) return false;
        final CustomerDTO other = (CustomerDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$customerName = this.getCustomerName();
        final Object other$customerName = other.getCustomerName();
        if (this$customerName == null ? other$customerName != null : !this$customerName.equals(other$customerName))
            return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        if (this.getVersion() != other.getVersion()) return false;
        final Object this$createdDate = this.getCreatedDate();
        final Object other$createdDate = other.getCreatedDate();
        if (this$createdDate == null ? other$createdDate != null : !this$createdDate.equals(other$createdDate))
            return false;
        final Object this$lastModifiedDate = this.getLastModifiedDate();
        final Object other$lastModifiedDate = other.getLastModifiedDate();
        if (this$lastModifiedDate == null ? other$lastModifiedDate != null : !this$lastModifiedDate.equals(other$lastModifiedDate))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CustomerDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $customerName = this.getCustomerName();
        result = result * PRIME + ($customerName == null ? 43 : $customerName.hashCode());
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        result = result * PRIME + this.getVersion();
        final Object $createdDate = this.getCreatedDate();
        result = result * PRIME + ($createdDate == null ? 43 : $createdDate.hashCode());
        final Object $lastModifiedDate = this.getLastModifiedDate();
        result = result * PRIME + ($lastModifiedDate == null ? 43 : $lastModifiedDate.hashCode());
        return result;
    }

    public String toString() {
        return "Customer(customerName=" + this.getCustomerName() + ", id=" + this.getId() + ", version=" + this.getVersion() + ", createdDate=" + this.getCreatedDate() + ", lastModifiedDate=" + this.getLastModifiedDate() + ")";
    }

    public static class CustomerBuilder {
        private String customerName;
        private UUID id;
        private Integer version;
        private LocalDateTime createdDate;
        private LocalDateTime lastModifiedDate;

        CustomerBuilder() {
        }

        public CustomerBuilder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public CustomerBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public CustomerBuilder version(Integer version) {
            this.version = version;
            return this;
        }

        public CustomerBuilder createdDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public CustomerBuilder lastModifiedDate(LocalDateTime lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
            return this;
        }

        public CustomerDTO build() {
            return new CustomerDTO(id, customerName, version, createdDate, lastModifiedDate);
        }

        public String toString() {
            return "Customer.CustomerBuilder(customerName=" + this.customerName + ", id=" + this.id + ", version=" + this.version + ", createdDate=" + this.createdDate + ", lastModifiedDate=" + this.lastModifiedDate + ")";
        }
    }
}
