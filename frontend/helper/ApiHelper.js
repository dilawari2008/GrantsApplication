import axios from "axios";

const API_URL = process.env.NEXT_PUBLIC_API_HOST;

const getFoundationDetails = async (username) => {
  const response = await axios({
    method: 'get',
    url: `${API_URL}/app/users/${username}`,
  });

  return response.data;
};

const getEmailsList = async (foundationId, pageSize, pageNumber, nonProfitId) => {
  const response = await axios({
    method: 'get',
    url: `${API_URL}/app/emails/listing/${foundationId}?pageSize=${pageSize || 5}&pageNumber=${pageNumber || 1}${nonProfitId ? `&nonProfitId=${nonProfitId ?? null}` : ``}`,
  });

  return response.data;
};

const getNonProfitsList = async (foundationId, pageSize, pageNumber) => {
  const response = await axios({
    method: 'get',
    url: `${API_URL}/app/non-profits/${foundationId}?pageSize=${pageSize || 5}&pageNumber=${pageNumber || 1}`,
  });

  return response.data;
};

const createFoundation = async (data) => {
  const response = await axios({
    method: 'post',
    url: `${API_URL}/app/foundations/`,
    data,
  });

  return response.data;
};

const createNonProfit = async (data) => {
  const response = await axios({
    method: 'post',
    url: `${API_URL}/app/non-profits/`,
    data,
  });

  return response.data;
};

const updateTemplate = async (data) => {
  const response = await axios({
    method: 'put',
    url: `${API_URL}/app/non-profits/template`,
    data,
  });

  return response.data;
};

const sendEmails = async (data) => {
  const response = await axios({
    method: 'post',
    url: `${API_URL}/app/emails/`,
    data,
  });

  return response.data;
};

const sendCustomEmail = async (data) => {
  const response = await axios({
    method: 'post',
    url: `${API_URL}/app/emails/custom`,
    data,
  });

  return response.data;
};

const ApiHelper = {
  getFoundationDetails,
  getEmailsList,
  getNonProfitsList,
  createFoundation,
  createNonProfit,
  updateTemplate,
  sendEmails,
  sendCustomEmail,
};

export default ApiHelper;