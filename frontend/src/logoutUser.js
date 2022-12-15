export const logoutUser = () => {
  console.log('User logged out');
  localStorage.removeItem('token');
  localStorage.removeItem('token_duration');
  localStorage.removeItem('userId');
};
