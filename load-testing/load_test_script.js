import http from 'k6/http';
import { check, sleep } from 'k6';
import { Rate } from 'k6/metrics';

export const errorRate = new Rate('errors');

export default function () {
  const url = 'http://localhost:8081/api/v1/account';
  const params = {
    headers: {
      'Authorization': 'Bearer token',
      'Content-Type': 'application/json',
    },
  };
  
  const data = JSON.stringify({
    userId: `000018b0e1a211ef95a30242ac180002`,
  });
  
  check(http.post(url, data, params), {
    'status is 200': (r) => r.status == 200,
  }) || errorRate.add(1);

  sleep(1);
}
