public class User {

        private String name;
        private int id;
        private String username;
        private String password;
        private String email;
        public User(String name, int id, String username, String password, String email) {
            this.name = name;
            this.id = id;
            this.username = username;
            this.password = password;
            this.email = email;
        }
    
        public int getId() {
            return id;
        }
    
        public String getUsername() {
            return username;
        }
    
        public String getPassword() {
            return password;
        }
    
        public String getEmail() {
            return email;
        }
        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setEmail(String email) {
            this.email = email;
        }
        
}

