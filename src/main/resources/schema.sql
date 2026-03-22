CREATE DATABASE IF NOT EXISTS HousingAid;

USE HousingAid;

CREATE TABLE Users
(
    userId BIGINT PRIMARY KEY AUTO_INCREMENT,
    userPassword VARCHAR(255) NOT NULL,
    userName VARCHAR(100) NOT NULL,
    userRole ENUM('CITIZEN','OFFICER','MANAGER','ADMIN','COMPLIANCE','AUDITOR') NOT NULL,
    userEmail VARCHAR(100) UNIQUE NOT NULL,
    userPhone VARCHAR(20),
    userStatus ENUM('ACTIVE','INACTIVE','SUSPENDED','PENDING') DEFAULT 'ACTIVE'
);

CREATE TABLE Citizen
(
    citizenId BIGINT PRIMARY KEY AUTO_INCREMENT,
    userId BIGINT UNIQUE NOT NULL,
    citizenDOB DATE NOT NULL,
    citizenGender ENUM('MALE','FEMALE','OTHER') NOT NULL,
    citizenAddress TEXT NOT NULL,
    citizenContactInfo VARCHAR(100),
    citizenVerificationStatus ENUM('ACTIVE','INACTIVE','VERIFIED','PENDING') DEFAULT 'PENDING',

    FOREIGN KEY (userId)
        REFERENCES Users(userId)
        ON DELETE CASCADE
);

CREATE TABLE HousingProject
(
    housingProjectId BIGINT PRIMARY KEY AUTO_INCREMENT,
    housingProjectTitle VARCHAR(200) NOT NULL,
    housingProjectDescription TEXT,
    numberOfHouses INT DEFAULT 0,
    housingProjectStartDate DATE,
    housingProjectEndDate DATE,
    housingProjectBudget DECIMAL(15,2),
    housingProjectStatus ENUM('PLANNED','UNDERPROGRESS','COMPLETED') DEFAULT 'PLANNED'
);

CREATE TABLE Resources
(
    resourcesId BIGINT PRIMARY KEY AUTO_INCREMENT,
    housingProjectId BIGINT NOT NULL,
    resourcesType ENUM('FUNDS','MATERIALS'),
    resourcesQuantity INT,
    resourcesStatus ENUM('ALLOCATED','INUSE','AVAILABLE'),

    FOREIGN KEY (housingProjectId)
        REFERENCES HousingProject(housingProjectId)
);

CREATE TABLE HousingUnit
(
    housingUnitId BIGINT PRIMARY KEY AUTO_INCREMENT,
    housingProjectId BIGINT NOT NULL,
    housingUnitLocation VARCHAR(200),
    housingUnitType ENUM('APARTMENT','SHELTER'),
    housingUnitCapacity INT DEFAULT 1,
    housingUnitStatus ENUM('AVAILABLE','ALLOCATED','MAINTENANCE') DEFAULT 'AVAILABLE',

    FOREIGN KEY (housingProjectId)
        REFERENCES HousingProject(housingProjectId)
);

CREATE TABLE HousingApplication
(
    housingApplicationId BIGINT PRIMARY KEY AUTO_INCREMENT,
    citizenId BIGINT NOT NULL,
    housingProjectId BIGINT NOT NULL,
    housingApplicationSubmittedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    housingApplicationStatus ENUM('PENDING','UNDERREVIEW','APPROVED','REJECTED') DEFAULT 'PENDING',

    FOREIGN KEY (citizenId)
        REFERENCES Citizen(citizenId),

    FOREIGN KEY (housingProjectId)
        REFERENCES HousingProject(housingProjectId)
);

CREATE TABLE EligibilityCheck
(
    eligibilityCheckId BIGINT PRIMARY KEY AUTO_INCREMENT,
    housingApplicationId BIGINT NOT NULL,
    officerId BIGINT NOT NULL,
    eligibilityCheckResult ENUM('ELIGIBLE','NOTELIGIBLE') NOT NULL,
    eligibilityCheckDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    eligibilityCheckNotes TEXT,

    FOREIGN KEY (housingApplicationId)
        REFERENCES HousingApplication(housingApplicationId),

    FOREIGN KEY (officerId)
        REFERENCES Users(userId)
);

CREATE TABLE Allocation
(
    allocationId BIGINT PRIMARY KEY AUTO_INCREMENT,
    eligibilityCheckId BIGINT NOT NULL UNIQUE,
    housingUnitId BIGINT NOT NULL UNIQUE,
    officerId BIGINT NOT NULL,
    allocationDate DATE,
    status ENUM('ALLOCATED','NOTALLOCATED') DEFAULT 'ALLOCATED',

    FOREIGN KEY (eligibilityCheckId)
        REFERENCES EligibilityCheck(eligibilityCheckId),

    FOREIGN KEY (housingUnitId)
        REFERENCES HousingUnit(housingUnitId),

    FOREIGN KEY (officerId)
        REFERENCES Users(userId)
);

CREATE TABLE Audit
(
    auditId BIGINT PRIMARY KEY AUTO_INCREMENT,
    officerId BIGINT NOT NULL,
    auditScope VARCHAR(255),
    auditFindings TEXT,
    auditDate DATE,
    auditStatus ENUM('PENDING','COMPLETED','FLAGGED'),

    FOREIGN KEY (officerId)
        REFERENCES Users(userId)
);

CREATE TABLE ComplianceRecord
(
    complianceRecordId BIGINT PRIMARY KEY AUTO_INCREMENT,
    entityId BIGINT NOT NULL,
    entityType ENUM('HOUSINGAPPLICATION','ALLOCATION','HOUSINGPROJECT') NOT NULL,
    complianceRecordResult ENUM('COMPLIANT','NONCOMPLIANT'),
    complianceRecordCheckDate DATE,
    complianceRecordNotes TEXT
);

CREATE TABLE CitizenDocument
(
    citizenDocumentId BIGINT PRIMARY KEY AUTO_INCREMENT,
    citizenId BIGINT NOT NULL,
    citizenDocumentDocType ENUM('IDPROOF','INCOMECERTIFICATE') NOT NULL,
    citizenDocumentFileUri VARCHAR(255) NOT NULL,
    citizenDocumentUploadedDate DATE NOT NULL,
    citizenDocumentVerificationStatus ENUM('PENDING','VERIFIED','REJECTED') DEFAULT 'PENDING',

    FOREIGN KEY (citizenId)
        REFERENCES Citizen(citizenId)
        ON DELETE CASCADE
);

CREATE TABLE AuditLog
(
    auditLogId BIGINT PRIMARY KEY AUTO_INCREMENT,
    userId BIGINT NOT NULL,
    auditLogAction ENUM('LOGIN','LOGOUT','CREATE','UPDATE','DELETE','VERIFY','REJECT','AUDITREVIEW','COMPLIANCECHECK') NOT NULL,
    auditLogResource VARCHAR(100),
    auditLogTimestamp DATETIME DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (userId)
        REFERENCES Users(userId)
        ON DELETE CASCADE
);

CREATE TABLE Report
(
    reportId BIGINT PRIMARY KEY AUTO_INCREMENT,
    reportScope ENUM('HOUSINGAPPLICATION','ALLOCATION','HOUSINGPROJECT'),
    reportSummary VARCHAR(500),
    housingApplicationId BIGINT NULL,
    allocationId BIGINT NULL,
    housingProjectId BIGINT NULL,
    reportGeneratedDate DATE DEFAULT (CURRENT_DATE),

    FOREIGN KEY (housingApplicationId)
        REFERENCES HousingApplication(housingApplicationId),

    FOREIGN KEY (allocationId)
        REFERENCES Allocation(allocationId),

    FOREIGN KEY (housingProjectId)
        REFERENCES HousingProject(housingProjectId)
);



-- SET FOREIGN_KEY_CHECKS = 0;
--
-- TRUNCATE Allocation;
-- TRUNCATE EligibilityCheck;
-- TRUNCATE HousingApplication;
-- TRUNCATE CitizenDocument;
-- TRUNCATE AuditLog;
-- TRUNCATE Audit;
-- TRUNCATE ComplianceRecord;
-- TRUNCATE Report;
-- TRUNCATE HousingUnit;
-- TRUNCATE Resources;
-- TRUNCATE HousingProject;
-- TRUNCATE Citizen;
-- TRUNCATE Users;
--
-- SET FOREIGN_KEY_CHECKS = 1;
--
-- INSERT INTO Users VALUES
--                       (1,'pass123','Rahul Sharma','CITIZEN','rahul@gmail.com','9876543210','ACTIVE'),
--                       (2,'pass123','Amit Verma','OFFICER','amit@gmail.com','9876543211','ACTIVE'),
--                       (3,'pass123','Neha Singh','MANAGER','neha@gmail.com','9876543212','ACTIVE'),
--                       (4,'pass123','Priya Das','ADMIN','priya@gmail.com','9876543213','ACTIVE'),
--                       (5,'pass123','Karan Patel','COMPLIANCE','karan@gmail.com','9876543214','ACTIVE'),
--                       (6,'pass123','Ravi Kumar','AUDITOR','ravi@gmail.com','9876543215','ACTIVE'),
--                       (7,'pass123','Sita Devi','CITIZEN','sita@gmail.com','9876543216','ACTIVE'),
--                       (8,'pass123','John Paul','OFFICER','john@gmail.com','9876543217','ACTIVE'),
--                       (9,'pass123','Anjali Mehta','CITIZEN','anjali@gmail.com','9876543218','ACTIVE'),
--                       (10,'pass123','Vikram Rao','OFFICER','vikram@gmail.com','9876543219','ACTIVE');
--
-- INSERT INTO Citizen VALUES
--                         (1,1,'1998-05-10','MALE','Delhi','Contact','VERIFIED'),
--                         (2,7,'1995-07-15','FEMALE','Mumbai','Contact','PENDING'),
--                         (3,9,'2000-01-20','FEMALE','Chennai','Contact','VERIFIED');
--
-- INSERT INTO HousingProject VALUES
--                                (1,'Urban Shelter','Affordable housing',100,'2024-01-01','2025-01-01',1000000,'UNDERPROGRESS'),
--                                (2,'City Homes','Low cost housing',200,'2024-02-01','2025-06-01',2000000,'PLANNED'),
--                                (3,'Green Residency','Eco housing',150,'2024-03-01','2025-05-01',1500000,'PLANNED');
--
-- INSERT INTO Resources VALUES
--                           (1,1,'FUNDS',500000,'ALLOCATED'),
--                           (2,2,'MATERIALS',200,'AVAILABLE'),
--                           (3,3,'FUNDS',300000,'INUSE');
--
-- INSERT INTO HousingUnit VALUES
--                             (1,1,'Block A','APARTMENT',4,'AVAILABLE'),
--                             (2,2,'Block B','SHELTER',6,'AVAILABLE'),
--                             (3,3,'Block C','APARTMENT',3,'AVAILABLE');
--
-- INSERT INTO HousingApplication VALUES
--                                    (1,1,1,NOW(),'PENDING'),
--                                    (2,2,2,NOW(),'UNDERREVIEW'),
--                                    (3,3,3,NOW(),'APPROVED');
--
-- INSERT INTO EligibilityCheck VALUES
--                                  (1,1,2,'ELIGIBLE',NOW(),'Income valid'),
--                                  (2,2,8,'NOTELIGIBLE',NOW(),'Income high'),
--                                  (3,3,10,'ELIGIBLE',NOW(),'Approved');
--
-- INSERT INTO Allocation VALUES
--                            (1,1,1,2,'2025-01-10','ALLOCATED'),
--                            (2,2,2,8,'2025-01-11','NOTALLOCATED'),
--                            (3,3,3,10,'2025-01-12','ALLOCATED');
--
-- INSERT INTO CitizenDocument VALUES
--                                 (1,1,'IDPROOF','file1.pdf','2025-01-01','VERIFIED'),
--                                 (2,2,'INCOMECERTIFICATE','file2.pdf','2025-01-02','PENDING'),
--                                 (3,3,'IDPROOF','file3.pdf','2025-01-03','VERIFIED');
--
-- INSERT INTO Audit VALUES
--                       (1,6,'Project Audit','No issues','2025-02-01','COMPLETED'),
--                       (2,6,'Allocation Audit','Minor delay','2025-02-02','FLAGGED'),
--                       (3,6,'Compliance','Good','2025-02-03','COMPLETED');
--
-- INSERT INTO AuditLog VALUES
--                          (1,1,'LOGIN','System',NOW()),
--                          (2,2,'CREATE','Application',NOW()),
--                          (3,3,'UPDATE','Project',NOW());
--
-- INSERT INTO ComplianceRecord VALUES
--                                  (1,1,'HOUSINGAPPLICATION','COMPLIANT','2025-03-01','OK'),
--                                  (2,1,'ALLOCATION','COMPLIANT','2025-03-02','OK'),
--                                  (3,1,'HOUSINGPROJECT','COMPLIANT','2025-03-03','OK');
--
-- INSERT INTO Report VALUES
--                        (1,'HOUSINGAPPLICATION','Application Report',1,NULL,NULL,CURDATE()),
--                        (2,'ALLOCATION','Allocation Report',NULL,1,NULL,CURDATE()),
--                        (3,'HOUSINGPROJECT','Project Report',NULL,NULL,1,CURDATE());

