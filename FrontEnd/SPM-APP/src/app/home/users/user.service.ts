import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject} from "rxjs";
import { shareReplay, tap} from "rxjs/operators";


export interface User {
  userId: string,
  userName: string,
  password: string,
  email: string
}


@Injectable({
  providedIn: 'root'
})
export class UserService {


  private BASE_URL: string = "http://Spmbookshop-env.eba-j93emkwq.us-east-1.elasticbeanstalk.com"

  private subject = new BehaviorSubject<User[]>([]);
  users$ = this.subject.asObservable();

  constructor(private http: HttpClient) {
    this.loadAllUsers()
  }

  loadAllUsers() {
    this.http.get<User[]>(`${this.BASE_URL}/users`)
      .pipe(
        shareReplay(),
        tap(courses => this.subject.next(courses))
      ).subscribe()
  }

  deleteUserById(id: string) {
    const users = this.subject.value;

    this.subject.next(users.filter(user => user.userId !== id))

    return this.http.delete(`${this.BASE_URL}/users/${id}`).subscribe()
  }

  createUser(user: User) {
    const userList = this.subject.getValue();

    const newCourses: User[] = userList.slice();
    newCourses.push(user);
    this.subject.next(newCourses);

    return this.http.post(`${this.BASE_URL}/users`, user)
      .pipe(
        shareReplay()
      )

  }

  findUser(id: string): User {
    return this.subject.value.filter(user => user.userId === id)[0];
  }

  updateUser(user: User) {
    const userList = this.subject.getValue();

    const newCourses: User[] = userList.slice();
    newCourses.push(user);
    this.subject.next(newCourses);

    return this.http.put(`${this.BASE_URL}/users`, user)
      .pipe(
        shareReplay()
      )
  }

}
