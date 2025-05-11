// context/AuthContext.tsx
import React, { createContext, useContext, useState, ReactNode } from 'react';

type AuthData = {
  token: string;
  id: string;
};

type AuthContextType = {
  auth: AuthData | null;
  login: (data: AuthData) => void;
  logout: () => void;
};

const AuthContext = createContext<AuthContextType>({
  auth: null,
  login: () => {},
  logout: () => {},
});

export const useAuth = () => useContext(AuthContext);

export const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [auth, setAuth] = useState<AuthData | null>(null);

  const login = (data: AuthData) => {
    setAuth(data);
    // Optional: Save to AsyncStorage here
  };

  const logout = () => {
    setAuth(null);
    // Optional: Clear AsyncStorage
  };

  return (
    <AuthContext.Provider value={{ auth, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
