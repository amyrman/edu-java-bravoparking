import { logoutUser } from './logoutUser';

export const checkJwtExpired = () => {
  const token_duration = localStorage.getItem('token_duration');
  if (Date.now() > token_duration) {
    console.log('TOKEN EXPIRED!');
    logoutUser();
  } else {
    console.log('Keep on scrollin!');
  }
};
