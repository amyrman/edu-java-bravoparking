export const logoutUser = () => {
  localStorage.removeItem('token');
  localStorage.removeItem('token_duration');
  localStorage.removeItem('userId');
  console.log('User logged out');
};
