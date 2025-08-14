CREATE TABLE IF NOT EXISTS `ola_user`(
    `user_id` INT(11) AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) ,
    `username` VARCHAR(255),
    `email` VARCHAR(255),
    `phone_number` VARCHAR(15) ,
     password VARCHAR(255),
    `address` VARCHAR(255),
    `city` VARCHAR(255) ,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `is_deleted` BOOLEAN NOT NULL,
    `first_login_at` DATETIME DEFAULT NULL,
    `last_login_at` DATETIME DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `ola_role`(
    `role_id` INT(11) AUTO_INCREMENT PRIMARY KEY,
    `role_name` VARCHAR(255) DEFAULT NULL,
    `role_description` VARCHAR(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `ola_role_user`(
    `role_user_id` INT(11) AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT(11) NOT NULL,
    `role_id` INT(11) NOT NULL,
    KEY `fk_role_user_user_id` (user_id),
    KEY `fk_role_user_role_id` (role_id),
    CONSTRAINT `fk_role_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `ola_user` (`user_id`),
    CONSTRAINT `fk_role_user_role_id` FOREIGN KEY (`role_id`) REFERENCES `ola_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `ola_driver` (
    `driver_id` INT(11) AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT(11) NOT NULL,
    `dob` VARCHAR(255) ,
    `vehicle_id` INT(11),
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `ola_vehicle` (
    `vehicle_id` INT(11) AUTO_INCREMENT PRIMARY KEY,
    `driver_id` INT(11) NOT NULL ,
    `adhaar_number` VARCHAR(255) DEFAULT NULL,
    `pan_number` VARCHAR(255) DEFAULT NULL,
    `license_number` VARCHAR(255) DEFAULT NULL,
    `license_expiry_date` VARCHAR(255) DEFAULT NULL,
    `vehicle_number` VARCHAR(255) DEFAULT NULL,
    `insurance_id` VARCHAR(255) DEFAULT NULL,
    `insurance_start_date` VARCHAR(255) DEFAULT NULL,
    `insurance_end_date` VARCHAR(255) DEFAULT NULL,
    `puc_id` VARCHAR(255) DEFAULT NULL,
    `puc_start_date` VARCHAR(255) DEFAULT NULL,
    `puc_end_date` VARCHAR(255) DEFAULT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


CREATE TABLE IF NOT EXISTS `ola_ride`(
    `ride_id` INT(11) AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT(11) NOT NULL,
    `driver_id` INT(11) ,
    `start_location` VARCHAR(255) NOT NULL,
    `current_location` VARCHAR(255) NOT NULL,
    `end_location` VARCHAR(255) NOT NULL,
    `start_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `end_time` DATETIME DEFAULT NULL,
    `status` VARCHAR(255) DEFAULT NULL,
    `fare` DECIMAL(10,2) NOT NULL,
    `distance_km` DECIMAL(10,2) DEFAULT NULL,
    `duration_min` INT(11) DEFAULT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `otp` VARCHAR(255),
    KEY `fk_ola_rides_user_id` (`user_id`),
    KEY `fk_ola_rides_driver_id` (`driver_id`),
    CONSTRAINT `fk_ola_rides_user_id` FOREIGN KEY (`user_id`) REFERENCES `ola_user` (`user_id`),
    CONSTRAINT `fk_ola_rides_driver_id` FOREIGN KEY (`driver_id`) REFERENCES `ola_driver` (`driver_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `ola_payment`(
    `payment_id` INT(11) AUTO_INCREMENT PRIMARY KEY,
    `ride_id` INT(11) NOT NULL,
    `user_id` INT(11) NOT NULL,
    `amount` DECIMAL(10,2) NOT NULL,
    `payment_method` VARCHAR(50) NOT NULL,
    `payment_status` VARCHAR(50) NOT NULL,
    `transaction_id` VARCHAR(100) DEFAULT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    KEY `fk_ola_payment_ride_id` (ride_id),
    KEY `fk_ola_payment_user_id` (user_id),
    CONSTRAINT `fk_ola_payment_ride_id` FOREIGN KEY (`ride_id`) REFERENCES `ola_ride` (`ride_id`),
    CONSTRAINT `fk_ola_payment_user_id` FOREIGN KEY (`user_id`) REFERENCES `ola_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `ola_location`(
    `location_id` INT(11) AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT(11) NOT NULL,
    `driver_id` INT(11) NOT NULL,
    `latitude` DECIMAL(10,6) NOT NULL,
    `longitude` DECIMAL(10,6) NOT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    KEY `fk_ola_location_user_id` (`user_id`),
    KEY `fk_ola_location_driver_id` (`driver_id`),
    CONSTRAINT `fk_ola_location_user_id` FOREIGN KEY (`user_id`) REFERENCES `ola_user` (`user_id`),
    CONSTRAINT `fk_ola_location_driver_id` FOREIGN KEY (`driver_id`) REFERENCES `ola_driver` (`driver_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


CREATE TABLE IF NOT EXISTS `ola_feedback` (
    `feedback_id` INT(11) AUTO_INCREMENT PRIMARY KEY,
    `ride_id` INT(11) NOT NULL,
    `user_id` INT(11) NOT NULL,
    `driver_id` INT(11) NOT NULL,
    `rating` INT(11) DEFAULT NULL,
    `comment` VARCHAR(255) DEFAULT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    KEY `fk_ola_feedback_ride_id` (`ride_id`),
    KEY `fk_ola_feedback_user_id` (`user_id`),
    KEY `fk_ola_feedback_driver_id` (`driver_id`),
    CONSTRAINT `fk_ola_feedback_ride_id` FOREIGN KEY (`ride_id`) REFERENCES `ola_ride` (`ride_id`),
    CONSTRAINT `fl_fk_ola_feedback_user_id` FOREIGN KEY (`user_id`) REFERENCES `ola_user` (`user_id`),
    CONSTRAINT `fk_ola_feedback_driver_id` FOREIGN KEY (`driver_id`) REFERENCES `ola_driver` (`driver_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


CREATE TABLE IF NOT EXISTS `ola_coupon`(
    `coupon_id` INT(11) AUTO_INCREMENT PRIMARY KEY,
    `code` VARCHAR(50) NOT NULL,
    `description` VARCHAR(255) DEFAULT NULL,
    `discount_percentage` DECIMAL(5, 2),
    `valid_from` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `valid_to` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `ola_wallet`(
    `wallet_id` INT(11) AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT(11) NOT NULL,
    `balance` DECIMAL(10,2) NOT NULL DEFAULT 0,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


CREATE TABLE IF NOT EXISTS `ola_ride_request`(
    `ride_request_id` INT(11) AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT(11) NOT NULL,
    `driver_id` INT(11) NOT NULL,
    `ride_id` INT(11) NOT NULL,
    `request_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `status` VARCHAR(50) NOT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    KEY `fk_ola_ride_request_user_id` (`user_id`),
    KEY `fk_ola_ride_request_driver_id` (`driver_id`),
    KEY `fk_ola_ride_request_ride_id` (`ride_id`),
    CONSTRAINT `fk_ola_ride_request_user_id` FOREIGN KEY (`user_id`) REFERENCES `ola_user` (`user_id`),
    CONSTRAINT `fk_ola_ride_request_driver_id` FOREIGN KEY (`driver_id`) REFERENCES `ola_driver` (`driver_id`),
    CONSTRAINT `fk_ola_ride_request_ride_id` FOREIGN KEY (`ride_id`) REFERENCES `ola_ride` (`ride_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


CREATE TABLE IF NOT EXISTS `ola_banking_details`(
    `banking_details_id` INT(11) AUTO_INCREMENT PRIMARY KEY,
    `driver_id` INT(11) NOT NULL,
    `Account_holder_name` VARCHAR(255) DEFAULT NULL,
    `bank_name` VARCHAR(255) DEFAULT NULL,
    `branch_name` VARCHAR(255) DEFAULT NULL,
    `ifsc_code` VARCHAR(255) DEFAULT NULL,
    `upi_id` VARCHAR(255) DEFAULT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    KEY `ola_banking_details_driver_id` (`driver_id`),
    CONSTRAINT `ola_banking_details_driver_id` FOREIGN KEY (`driver_id`) REFERENCES `ola_driver` (`driver_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

ALTER TABLE `ola_driver` ADD CONSTRAINT `fk_ola_driver_vehicle_id`
    FOREIGN KEY (`vehicle_id`) REFERENCES `ola_vehicle` (`vehicle_id`);

ALTER TABLE `ola_vehicle` ADD CONSTRAINT `fk_ola_vehicle_driver_id`
    FOREIGN KEY (`driver_id`) REFERENCES `ola_driver` (`driver_id`);

INSERT INTO `ola_role` (`role_id`, `role_name`, `role_description`) VALUES ('1','ADMIN', 'ADMIN');
INSERT INTO `ola_role` (`role_id`, `role_name`, `role_description`) VALUES ('2','DRIVER', 'DRIVER');
INSERT INTO `ola_role` (`role_id`, `role_name`, `role_description`) VALUES ('3','USER', 'USER');

CREATE TABLE IF NOT EXISTS `ola_message` (
    `message_id` INT(11) AUTO_INCREMENT PRIMARY KEY,
    `ride_id` INT(11) NOT NULL,
    `sender_id` INT(11) NOT NULL,
    `receiver_id` INT(11) NOT NULL,
    `message` VARCHAR(255) NOT NULL,
    `is_received` BOOLEAN NOT NULL,
    `is_seen` BOOLEAN NOT NULL,
    `timestamp` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `fk_ola_message_ride_id` FOREIGN KEY (`ride_id`) REFERENCES `ola_ride` (`ride_id`),
    CONSTRAINT `fk_ola_message_sender_id` FOREIGN KEY (`sender_id`) REFERENCES `ola_user` (`user_id`),
    CONSTRAINT `fk_ola_message_receiver_id` FOREIGN KEY (`receiver_id`) REFERENCES `ola_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `ola_term_and_condition` (
    `t_c_id` INT(11) AUTO_INCREMENT PRIMARY KEY,
    `general_terms` TEXT DEFAULT NULL,
    `booking_and_reservations` TEXT DEFAULT NULL,
    `passenger_responsibilities` TEXT DEFAULT NULL,
    `driver_responsibilities` TEXT DEFAULT NULL,
    `fares_and_payments` TEXT DEFAULT NULL,
    `cancellations_and_refunds` TEXT DEFAULT NULL,
    `liability` TEXT DEFAULT NULL,
    `complaints_and_disputes` TEXT DEFAULT NULL,
    `amendments` TEXT DEFAULT NULL,
    `privacy_policy` TEXT DEFAULT NULL,
    `contact_information` TEXT DEFAULT NULL,
    `effective_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `version` VARCHAR(50) DEFAULT NULL,
    `last_updated` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

INSERT INTO `ola_term_and_condition` (
    `general_terms`,
    `booking_and_reservations`,
    `passenger_responsibilities`,
    `driver_responsibilities`,
    `fares_and_payments`,
    `cancellations_and_refunds`,
    `liability`,
    `complaints_and_disputes`,
    `amendments`,
    `privacy_policy`,
    `contact_information`,
    `effective_date`,
    `version`,
    `last_updated`
) VALUES (
    'These terms and conditions govern the use of our taxi services.',
    'Bookings can be made through our app or website. Reservations must be confirmed to be valid.',
    'Passengers must behave respectfully and follow all safety guidelines during the ride.',
    'Drivers are responsible for driving safely and maintaining the vehicle in good condition.',
    'Fares are calculated based on distance and time. Payments can be made via credit card or other accepted methods.',
    'Cancellations must be made at least 30 minutes before the scheduled pickup time for a full refund. Refunds will be processed within 7 business days.',
    'Our company is not liable for any indirect, incidental, or consequential damages arising from the use of our services.',
    'Complaints and disputes should be reported through our customer service. We aim to resolve issues within 48 hours.',
    'We reserve the right to amend these terms and conditions at any time. Changes will be effective immediately upon posting.',
    'We value your privacy and will not share your personal information with third parties without your consent.',
    'For any questions or concerns, please contact us at support@example.com or call us at 123-456-7890.',
    '2024-06-11 00:00:00',
    '1.0',
    '2024-06-11 00:00:00'
);
