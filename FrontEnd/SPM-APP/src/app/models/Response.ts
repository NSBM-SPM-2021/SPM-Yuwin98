export interface Response<T> {
  message: String;
  statusCode: Number;
  data: T;
}

