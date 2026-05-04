CREATE TABLE points (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(50) NOT NULL UNIQUE,
                        type VARCHAR(50),
                        status VARCHAR(50),
                        line1 VARCHAR(255),
                        line2 VARCHAR(255),
                        latitude DOUBLE,
                        longitude DOUBLE
);

CREATE TABLE point_functions (
                                 point_id BIGINT NOT NULL,
                                 function_name VARCHAR(100) NOT NULL,
                                 CONSTRAINT fk_point_functions_point FOREIGN KEY (point_id) REFERENCES points (id)
);