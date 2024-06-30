import axios from "axios";

const API_URL = process.env.NEXT_PUBLIC_API_HOST;

const getFoundationDetails = async (username) => {
  const response = await axios({
    method: 'get',
    url: `${API_URL}/app/users/${username}`,
    // Optional: Add headers, data, params as needed
  });

  return response.data;
};

const ApiHelper = {
  getFoundationDetails,
};

export default ApiHelper;