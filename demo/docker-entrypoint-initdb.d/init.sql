-- Create the User table
CREATE TABLE UserApp (
                         user_id SERIAL PRIMARY KEY,
                         username VARCHAR(255) NOT NULL
);

-- Insert initial data
INSERT INTO UserApp (username) VALUES ('John');
INSERT INTO UserApp (username) VALUES ('Jane');