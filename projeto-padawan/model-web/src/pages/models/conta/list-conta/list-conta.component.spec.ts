/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { ListContaComponent } from './list-conta.component';

describe('ListContaComponent', () => {
  let component: ListContaComponent;
  let fixture: ComponentFixture<ListContaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListContaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListContaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
