public record CredentialDto(String cardFormat, int facilityCode, int BadgeId, BillingType billingType) {
}

enum BillingType
{
    Basic,
    Premium
}