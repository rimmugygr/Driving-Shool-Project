import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {RouterModule} from '@angular/router';
import {AppRoutingModule} from './app-routing.module';
import {FooterComponent} from './layout/footer.component';
import {NavbarComponent} from './layout/navbar.component';
import {SidebarComponent} from './layout/sidebar.component';
import {WhenDirective} from './directive/when.directive';
import {AddTargetDirective} from './directive/add-target.directive';
import {AttachToDirective} from './directive/attach-to.directive';
import { HomeComponent } from './pages/home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {FlexLayoutModule} from '@angular/flex-layout';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatListModule} from '@angular/material/list';
import { StudentListComponent } from './pages/student-list/student-list.component';
import {HttpClientModule} from '@angular/common/http';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import { StudentEditModalComponent } from './pages/student-list/components/student-edit-modal/student-edit-modal.component';
import { StudentDeleteModalComponent } from './pages/student-list/components/student-delete-modal/student-delete-modal.component';
import { StudentAddModalComponent } from './pages/student-list/components/student-add-modal/student-add-modal.component';
import {MatSelectModule} from '@angular/material/select';
import { TeacherListComponent } from './pages/teacher-list/teacher-list.component';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { TeacherEditModalComponent } from './pages/teacher-list/components/teacher-edit-modal/teacher-edit-modal.component';
import { TeacherDeleteModalComponent } from './pages/teacher-list/components/teacher-delete-modal/teacher-delete-modal.component';
import { TeacherAddModalComponent } from './pages/teacher-list/components/teacher-add-modal/teacher-add-modal.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    NavbarComponent,
    SidebarComponent,
    WhenDirective,
    AddTargetDirective,
    AttachToDirective,
    HomeComponent,
    StudentListComponent,
    StudentEditModalComponent,
    StudentDeleteModalComponent,
    StudentAddModalComponent,
    TeacherListComponent,
    TeacherEditModalComponent,
    TeacherDeleteModalComponent,
    TeacherAddModalComponent
  ],
    imports: [
        BrowserModule,
        NgbModule,
        RouterModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        FlexLayoutModule,
        MatButtonModule,
        MatSidenavModule,
        MatToolbarModule,
        MatListModule,
        HttpClientModule,
        MatTableModule,
        MatPaginatorModule,
        MatSortModule,
        MatCardModule,
        MatFormFieldModule,
        MatInputModule,
        MatIconModule,
        MatSelectModule,
        MatButtonToggleModule,
        FormsModule,
        ReactiveFormsModule,
        MatProgressSpinnerModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
