import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {share, shareReplay, tap} from "rxjs/operators";
import {Response} from "../../models/Response";


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


  private BASE_URL: string = "http://localhost:8080"

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

}
