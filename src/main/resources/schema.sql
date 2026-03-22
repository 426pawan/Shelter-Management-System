-- HousingAid database

create database if not exists HousingAid;

use HousingAid;


-- Identity & Access Management

CREATE TABLE Users (
                       userId INT PRIMARY KEY AUTO_INCREMENT,
                       userPassword VARCHAR(255) NOT NULL,
                       userName VARCHAR(100) NOT NULL,
                       userRole ENUM('CITIZEN','OFFICER','MANAGER','ADMIN','COMPLIANCE','AUDITOR') NOT NULL,
                       userEmail VARCHAR(100) UNIQUE NOT NULL,
                       userPhone VARCHAR(20),
                       userStatus ENUM('ACTIVE','INACTIVE','SUSPENDED','PENDING') DEFAULT 'ACTIVE'
);

CREATE TABLE Citizen (
                         citizenId INT PRIMARY KEY AUTO_INCREMENT,
                         userId INT UNIQUE NOT NULL,
                         citizenDOB DATE NOT NULL,
                         citizenGender ENUM('MALE','FEMALE','OTHER') NOT NULL,
                         citizenAddress TEXT NOT NULL,
                         citizenContactInfo VARCHAR(100),
                         citizenVerificationStatus ENUM('ACTIVE','INACTIVE','VERIFIED','PENDING') DEFAULT 'PENDING',
                         FOREIGN KEY (userId) REFERENCES Users(userId) ON DELETE CASCADE
);


-- Projects & Resources

CREATE TABLE HousingProject (
                                housingProjectId INT PRIMARY KEY AUTO_INCREMENT,
                                housingProjectTitle VARCHAR(200) NOT NULL,
                                housingProjectDescription TEXT,
                                numberOfHouses INT DEFAULT 0,
                                housingProjectStartDate DATE,
                                housingProjectEndDate DATE,
                                housingProjectBudget DECIMAL(15, 2),
                                housingProjectStatus ENUM('PLANNED', 'UNDERPROGRESS', 'COMPLETED') DEFAULT 'PLANNED'
);

CREATE TABLE Resources (
                           resourcesId INT PRIMARY KEY AUTO_INCREMENT,
                           housingProjectId INT NOT NULL,
                           resourcesType ENUM('FUNDS', 'MATERIALS'),
                           resourcesQuantity INT,
                           resourcesStatus ENUM('ALLOCATED', 'INUSE', 'AVAILABLE'),
                           FOREIGN KEY (housingProjectId) REFERENCES HousingProject(housingProjectId)
);

CREATE TABLE HousingUnit (
                             housingUnitId INT PRIMARY KEY AUTO_INCREMENT,
                             housingProjectId INT NOT NULL,
                             housingUnitLocation VARCHAR(200),
                             housingUnitType ENUM('APARTMENT', 'SHELTER'),
                             housingUnitCapacity INT DEFAULT 1,
                             housingUnitStatus ENUM('AVAILABLE','ALLOCATED','MAINTENANCE') DEFAULT 'AVAILABLE',
                             FOREIGN KEY (housingProjectId) REFERENCES HousingProject(housingProjectId)
);


-- Application & Allocation Workflow

CREATE TABLE HousingApplication (
                                    housingApplicationId INT PRIMARY KEY AUTO_INCREMENT,
                                    citizenId INT NOT NULL,
                                    housingProjectId INT NOT NULL,
                                    housingApplicationSubmittedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
                                    housingApplicationStatus ENUM('PENDING','UNDERREVIEW','APPROVED','REJECTED') DEFAULT 'PENDING',
                                    FOREIGN KEY (citizenId) REFERENCES Citizen(citizenId),
                                    FOREIGN KEY (housingProjectId) REFERENCES HousingProject(housingProjectId)
);

CREATE TABLE EligibilityCheck (
                                  eligibilityCheckId INT PRIMARY KEY AUTO_INCREMENT,
                                  housingApplicationId INT NOT NULL,
                                  officerId INT NOT NULL,
                                  eligibilityCheckResult ENUM('ELIGIBLE','NOTELIGIBLE') NOT NULL,
                                  eligibilityCheckDate DATETIME DEFAULT CURRENT_TIMESTAMP,
                                  eligibilityCheckNotes TEXT,
                                  FOREIGN KEY (housingApplicationId) REFERENCES HousingApplication(housingApplicationId),
                                  FOREIGN KEY (officerId) REFERENCES Users(userId)
);

CREATE TABLE Allocation (
                            allocationId INT PRIMARY KEY AUTO_INCREMENT,
                            eligibilityCheckId INT NOT NULL UNIQUE,
                            housingUnitId INT NOT NULL UNIQUE,
                            officerId INT NOT NULL,
                            allocationDate DATE,
                            status ENUM('ALLOCATED','NOTALLOCATED') DEFAULT 'ALLOCATED',
                            FOREIGN KEY (eligibilityCheckId) REFERENCES EligibilityCheck(eligibilityCheckId),
                            FOREIGN KEY (housingUnitId) REFERENCES HousingUnit(housingUnitId),
                            FOREIGN KEY (officerId) REFERENCES Users(userId)
);


-- Compliance & Audit

CREATE TABLE Audit (
                       auditId INT PRIMARY KEY AUTO_INCREMENT,
                       officerId INT NOT NULL,
                       auditScope VARCHAR(255),
                       auditFindings TEXT,
                       auditDate DATE,
                       auditStatus ENUM('PENDING','COMPLETED','FLAGGED'),
                       FOREIGN KEY (officerId) REFERENCES Users(userId)
);

CREATE TABLE ComplianceRecord (
                                  complianceRecordId INT PRIMARY KEY AUTO_INCREMENT,
                                  entityId INT NOT NULL,
                                  entityType ENUM('HOUSINGAPPLICATION', 'ALLOCATION', 'HOUSINGPROJECT') NOT NULL,
                                  complianceRecordResult ENUM('COMPLIANT','NONCOMPLIANT'),
                                  complianceRecordCheckDate DATE,
                                  complianceRecordNotes TEXT
);

CREATE TABLE CitizenDocument (
                                 citizenDocumentId INT PRIMARY KEY AUTO_INCREMENT,
                                 citizenId INT NOT NULL,
                                 citizenDocumentDocType ENUM('IDPROOF','INCOMECERTIFICATE') NOT NULL,
                                 citizenDocumentFileUri VARCHAR(255) NOT NULL,
                                 citizenDocumentUploadedDate DATE NOT NULL,
                                 citizenDocumentVerificationStatus ENUM('PENDING','VERIFIED','REJECTED') DEFAULT 'PENDING',
                                 FOREIGN KEY (citizenId) REFERENCES Citizen(citizenId) ON DELETE CASCADE
);

CREATE TABLE AuditLog (
                          auditLogId INT PRIMARY KEY AUTO_INCREMENT,
                          userId INT NOT NULL,
                          auditLogAction ENUM('LOGIN','LOGOUT','CREATE','UPDATE','DELETE','VERIFY','REJECT','AUDITREVIEW','COMPLIANCECHECK') NOT NULL,
                          auditLogResource VARCHAR(100),
                          auditLogTimestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (userId) REFERENCES Users(userId) ON DELETE CASCADE
);

CREATE TABLE Report (
                        reportId INT PRIMARY KEY AUTO_INCREMENT,
                        reportScope ENUM('HOUSINGAPPLICATION', 'ALLOCATION', 'HOUSINGPROJECT'),
                        reportSummary VARCHAR(500),
                        housingApplicationId INT NULL,
                        allocationId INT NULL,
                        housingProjectId INT NULL,
                        reportGeneratedDate DATE DEFAULT (CURRENT_DATE),
                        FOREIGN KEY (housingApplicationId) REFERENCES HousingApplication(housingApplicationId),
                        FOREIGN KEY (allocationId) REFERENCES Allocation(allocationId),
                        FOREIGN KEY (housingProjectId) REFERENCES HousingProject(housingProjectId)
);



